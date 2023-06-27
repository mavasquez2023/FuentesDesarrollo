

package cl.araucana.fpg.compiled.instructions;


/*
 * @(#) InstructionFactory.java    1.0 12-04-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


import java.lang.reflect.Method;

import cl.araucana.core.util.ByteArray;
import cl.araucana.core.util.TokenParser;

import cl.araucana.fpg.FPGException;

import cl.araucana.fpg.barcodes.EAN128BarCode;
import cl.araucana.fpg.barcodes.PDF417BarCode;

import cl.araucana.fpg.compiled.Expression;
import cl.araucana.fpg.compiled.FPGCode;
import cl.araucana.fpg.compiled.FPGLanguage;


/**
 * ...
 *
 * <BR>
 *
 * <TABLE BORDER="1" WIDTH="100%" CELLPADDING="3" CELLSPACING="0" SUMMARY="">
 *    <TBODY>
 *        <TR BGCOLOR="#CCCCFF" CLASS="TableHeadingColor">
 *            <TH ALIGN="left" COLSPAN=4> <FONT SIZE="+2">
 *                 <B>Registro de Mantenciones</B></FONT>
 *            </TH>
 *        </TR>
 *
 *        <TR>
 *            <TD align="center"> <B>Fecha</B> </TD>
 *            <TD align="center"> <B>Versión</B> </TD>
 *            <TD> <B>Autor</B> </TD>
 *            <TD align="left"><B>Descripción</B></TD>
 *        </TR>
 *
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 12-04-2008 </TD>
 *            <TD align="center">  1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *        
 *        <TR BGCOLOR="white" CLASS="TableRowColor">
 *            <TD> 14-03-2010 </TD>
 *            <TD align="center">  1.1 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Extiende decoder para soportar EAN128. </TD>
 *        </TR>
 *        
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class InstructionFactory implements FPGLanguage {

	private static final String[] decoderNames = {
        "barcode_ean128",
        "barcode_pdf417",
        "begin_cache",
        "dup",
        "dupn",
        "end",
        "end_cache",
        "exp",
        "expx",
        "font",
        "image",
        "include_cache",
        "index_prop",
        "index_var",
        "jump",
        "locate",
        "set_boolean_var",
        "set_image_var",
        "set_int_var",
        "set_string_var",
        "test_boolean_eq",
        "test_boolean_ge",
        "test_boolean_gt",
        "test_boolean_le",
        "test_boolean_lt",
        "test_boolean_ne",
        "test_int_eq",
        "test_int_ge",
        "test_int_gt",
        "test_int_le",
        "test_int_lt",
        "test_int_neq",
        "test_string_eq",
        "test_string_ge",
        "test_string_gt",
        "test_string_le",
        "test_string_lt",
        "test_string_ne",
        "text",
        "text_pad",
        "xybase"
	};

	private static final Method[] decoderMethods =
			new Method[decoderNames.length];

	private static final Class[] emptyArgs = new Class[0];

	private Decoder decoder = new Decoder();
	private ByteArray array;
	private int beginIndex;
	private String label;

	private boolean debug;

	static {
		Class clazz = InstructionFactory.Decoder.class;
		Class[] parameterTypes = new Class[0];

		for (int i = 0; i < decoderNames.length; i++) {
			try {
				decoderMethods[i] =
						clazz.getMethod(decoderNames[i], parameterTypes);
			} catch (Exception e) {
				System.out.println(
						  "Unexpected error with decoder "
						+ "'" + decoderNames[i] + "'.");
			}
		}
	}

	public InstructionFactory() {
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public boolean isDebug() {
		return debug;
	}

	public Instruction decode(String name, ByteArray array, int beginIndex)
			throws FPGException {

		int index = name.indexOf(':');

		String codeName = name;

		if (index != -1) {
			label = name.substring(0, index);
			codeName = name.substring(index + 1);
		} else {
			label = null;
		}

		int code = FPGCode.getCode(codeName);

		if (code == NOT_FOUND) {
			throw new FPGException("Unknown code '" + codeName + "'.");
		}

		this.array = array;
		this.beginIndex = beginIndex;

		try {
			Instruction $inst =
					(Instruction) decoderMethods[code].invoke(
							decoder, emptyArgs);

			if (debug) {
				System.err.print("FPGLinker: " + $inst);
			}

			return $inst;
		} catch (Exception e) {
			throw new FPGException("Decoder for '" + codeName + "' failed.", e);
		}
	}

	private class Decoder {

		public Instruction barcode_ean128() {
			String[] tokens = getTokens();
			int propIndex = Integer.parseInt(tokens[0]);
			char charset = tokens[1].charAt(0);
			int width = Integer.parseInt(tokens[2]);
			int height = Integer.parseInt(tokens[3]);
			int y0 = Integer.parseInt(tokens[4]);
			int x0 = Integer.parseInt(tokens[5]);
			boolean horizontal = Boolean.valueOf(tokens[6]).booleanValue();

			EAN128BarCode barCode =
					new EAN128BarCode(
							charset, width, height, y0, x0, horizontal);

			BarCodeEAN128Instruction $ean128 =
					new BarCodeEAN128Instruction(label, barCode, propIndex);

            return $ean128;
		}

		public Instruction barcode_pdf417() {
			String[] tokens = getTokens();
			int propIndex = Integer.parseInt(tokens[0]);
			int codeColumns = Integer.parseInt(tokens[1]);
			int errorLevel = Integer.parseInt(tokens[2]);
			int y0 = Integer.parseInt(tokens[3]);
			int x0 = Integer.parseInt(tokens[4]);
			boolean horizontal = Boolean.valueOf(tokens[5]).booleanValue();

			PDF417BarCode barCode =
					new PDF417BarCode(
								codeColumns, errorLevel, y0, x0, horizontal);

			BarCodePDF417Instruction $pdf417 =
					new BarCodePDF417Instruction(label, barCode, propIndex);

            return $pdf417;
		}

		public Instruction begin_cache() {
			String[] tokens = getTokens();
			String name = tokens[0];
			String jumpLabel= tokens[1];

			BeginCacheInstruction $beginCache =
					new BeginCacheInstruction(label, name, jumpLabel);

            return $beginCache;
		}

		public Instruction dup() {
			DupInstruction $dup = new DupInstruction(label);
			byte[] data = array.getBytes(beginIndex, array.length());

			$dup.setData(data, 0, data.length);

            return $dup;
		}

		public Instruction dupn() {
			DupInstruction $dupn = new DupInstruction(label, true);
			byte[] data = array.getBytes(beginIndex, array.length());

			$dupn.setData(data, 0, data.length - 1);

            return $dupn;
		}

		public Instruction end() {
			EndInstruction $end = new EndInstruction(label);

            return $end;
		}

		public Instruction end_cache() {
			String[] tokens = getTokens();
			String name = tokens[0];

			EndCacheInstruction $endCache =
					new EndCacheInstruction(label, name);

            return $endCache;
		}

		public Instruction exp() {
            return _exp(false);
		}

		public Instruction expx() {
			return _exp(true);
		}

		private Instruction _exp(boolean builtin) {
			String[] tokens = getTokens();
			int propIndex = Integer.parseInt(tokens[0]);
			ExpInstruction $exp = new ExpInstruction(label, builtin, propIndex);

            return $exp;
		}

		public Instruction font() {
			String[] tokens = getTokens();
			String sType = tokens[0];
			String sValue = tokens[1];

			Expression expr =
					Expression.getExpression(sType, TYPE_STRING, sValue);

			FontInstruction $font = new FontInstruction(label, expr);

            return $font;
		}

		public Instruction image() {
			String[] tokens = getTokens();
			int type = Integer.parseInt(tokens[0]);
			int propIndex = Integer.parseInt(tokens[1]);

			ImageInstruction $image =
					new ImageInstruction(label, type, propIndex);

			return $image;
		}

		public Instruction include_cache() {
			String[] tokens = getTokens();
			String name = tokens[0];

			IncludeCacheInstruction $includeCache =
					new IncludeCacheInstruction(label, name);

            return $includeCache;
		}

		public Instruction index_prop() {
            return _index("1");
		}

		public Instruction index_var() {
            return _index("0");
		}

		private Instruction _index(String sType) {
			String[] tokens = getTokens();
			String sValue = tokens[0];
			Expression expr = Expression.getExpression(sType, TYPE_INT, sValue);
			int nPositions = Integer.parseInt(tokens[1]);
			int[] positions = new int[nPositions];
			int index = 2;

			for (int i = 0; i < positions.length; i++) {
				positions[i] = Integer.parseInt(tokens[index++]);
			}

			int nValues = Integer.parseInt(tokens[index++]);
			String[] values = new String[nValues];

			for (int i = 0; i < values.length; i++) {
				values[i] = tokens[index++];
			}

			IndexInstruction $index =
					new IndexInstruction(label, expr, positions, values);

            return $index;
		}

		public Instruction jump() {
			String[] tokens = getTokens();
			String jumpLabel = tokens[0];
			JumpInstruction $jump = new JumpInstruction(label, jumpLabel);

            return $jump;
		}

		public Instruction xybase() {
			String[] tokens = getTokens();
			String xybase = tokens[0];
			XYBaseInstruction $xybase = new XYBaseInstruction(label, xybase);

            return $xybase;
		}

		public Instruction locate() {
			String[] tokens = getTokens();
			String yType = tokens[0];
			String yValue = tokens[1];
			String xType = tokens[2];
			String xValue = tokens[3];
			Expression y = Expression.getExpression(yType, TYPE_INT, yValue);
			Expression x = Expression.getExpression(xType, TYPE_INT, xValue);
			LocateInstruction $locate = new LocateInstruction(label, y, x);

            return $locate;
		}

		public Instruction set_boolean_var() {
            return _set_var(TYPE_BOOLEAN);
		}

		public Instruction set_image_var() {
            return _set_var(TYPE_IMAGE);
		}

		public Instruction set_int_var() {
			return _set_var(TYPE_INT);
		}

		public Instruction set_string_var() {
            return _set_var(TYPE_STRING);
		}

		private Instruction _set_var(int type) {
			String[] tokens = getTokens();
			int varIndex = Integer.parseInt(tokens[0]);
			int assignOperator = Integer.parseInt(tokens[1]);
			String sType = tokens[2];
			String sValue = tokens[3];

			Expression expr =
					Expression.getExpression(sType, type, sValue);

			SetInstruction $set =
					new SetInstruction(
							label, varIndex, type, assignOperator, expr);

			return $set;
		}

		public Instruction test_boolean_eq() {
            return _test(TYPE_BOOLEAN, OP_CMP_EQ);
		}

		public Instruction test_boolean_ge() {
            return _test(TYPE_BOOLEAN, OP_CMP_GE);
		}

		public Instruction test_boolean_gt() {
            return _test(TYPE_BOOLEAN, OP_CMP_GT);
		}

		public Instruction test_boolean_le() {
            return _test(TYPE_BOOLEAN, OP_CMP_LE);
		}

		public Instruction test_boolean_lt() {
            return _test(TYPE_BOOLEAN, OP_CMP_LT);
		}

		public Instruction test_boolean_ne() {
            return _test(TYPE_BOOLEAN, OP_CMP_NE);
		}

		public Instruction test_int_eq() {
            return _test(TYPE_INT, OP_CMP_EQ);
		}

		public Instruction test_int_ge() {
            return _test(TYPE_INT, OP_CMP_GE);
		}

		public Instruction test_int_gt() {
            return _test(TYPE_INT, OP_CMP_GT);
		}

		public Instruction test_int_le() {
            return _test(TYPE_INT, OP_CMP_LE);
		}

		public Instruction test_int_lt() {
            return _test(TYPE_INT, OP_CMP_LT);
		}
		public Instruction test_int_neq() {
			return _test(TYPE_INT, OP_CMP_NE);
		}

		public Instruction test_string_eq() {
            return _test(TYPE_STRING, OP_CMP_EQ);
		}

		public Instruction test_string_ge() {
            return _test(TYPE_STRING, OP_CMP_GE);
		}

		public Instruction test_string_gt() {
            return _test(TYPE_STRING, OP_CMP_GT);
		}

		public Instruction test_string_le() {
            return _test(TYPE_STRING, OP_CMP_LE);
		}

		public Instruction test_string_lt() {
            return _test(TYPE_STRING, OP_CMP_LT);
		}

		public Instruction test_string_ne() {
            return _test(TYPE_STRING, OP_CMP_NE);
		}

		private Instruction _test(int type, int operator) {
			String[] tokens = getTokens();
			String sType1 = tokens[0];
			String sValue1 = tokens[1];
			String sType2 = tokens[2];
			String sValue2 = tokens[3];
			String jumpLabel = tokens[4];

			Expression expr1 =
					Expression.getExpression(sType1, type, sValue1);

			Expression expr2 =
					Expression.getExpression(sType2, type, sValue2);

			TestInstruction $test =
					new TestInstruction(
							label, expr1, operator, expr2, jumpLabel);

            return $test;
		}

		public Instruction text() {
			String[] tokens = getTokens();
			int propIndex = Integer.parseInt(tokens[0]);
			TextInstruction $text = new TextInstruction(label, propIndex);

            return $text;
		}

		public Instruction text_pad() {
			String[] tokens = getTokens();
			int propIndex = Integer.parseInt(tokens[0]);
			int padding = Integer.parseInt(tokens[1]);

			TextInstruction $text =
					new TextInstruction(label, propIndex, padding);

            return $text;
		}
	}

	private String[] getTokens() {
		int aLength = array.length();

		if (aLength >= 2) {
			if (array.getByteAt(aLength - 2) == '\r') {
				aLength = aLength - 2;
			} else {
				aLength = aLength - 1;
			}
		} else {
			aLength = 0;
		}

		return TokenParser.parseTokens(array.getString(beginIndex, aLength));
	}
}
