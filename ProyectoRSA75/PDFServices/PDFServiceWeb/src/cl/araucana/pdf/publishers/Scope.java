

/*
 * @(#) Scope.java    1.0 24-05-2008
 *
 * Este código fuente pertenece a la Caja de Compensación de Asignación Familiar
 * La Araucana (C.C.A.F.). Su utilización y reproducción es confidencial y está
 * restringido a los sistemas de información propios de la institución.
 */


package cl.araucana.pdf.publishers;


import cl.araucana.core.util.AbsoluteDate;

import java.io.Serializable;


/**
 * This class is used to specify a target set of published PDF documents to
 * be operated, for example, to unpublish or retrieval operations with a
 * <b>PDF Publisher</b>. A <b>Scope</b> can be simple or composed and it referes
 * to one or more document type <u>fields</u> and their corresponding
 * <u>operators</u> and <u>values</u> to be matched. 
 *
 * <p> Some methods requires that scope is in <b>completed</b> state, otherwise
 * these will throw {@link IllegalArgumentException} unchecked exception.
 * </p>
 * 
 * <BR>
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
 *            <TD> 24-05-2008 </TD>
 *            <TD align="center"> 1.0 </TD>
 *            <TD> Germán Pavez I. <BR> gpavez@hotmail.com </TD>
 *            <TD> Versión inicial. </TD>
 *        </TR>
 *    </TBODY>
 * </TABLE>
 *
 * <BR>
 *
 * @author Germán Pavez I. (gpavez@hotmail.com)
 *
 * @version 1.0
 */
public class Scope implements Serializable {

	private static final long serialVersionUID = -5494922799990215630L;

	/**
	 *  Represents all published PDF document instances to the corresponding
	 *  document type. 
	 */
	public static final Scope ALL = new Scope("");

	private static final int LEAF    = 1;
	private static final int AND     = 2;
	private static final int OR      = 3;
	private static final int NOT     = 4;

	private static final AbsoluteDate zeroDate = new AbsoluteDate(31, 12, 1969);

	private int type;
	private Field[] fields;
	private Operator[] operators;
	private Object[] values;

	private Scope scope1;
	private Scope scope2;

	private boolean completed;
	private int offset;
	private String predicate;

	/**
	 * Converts the <code>date<code> specified in <b>YYYYMMDD</b> format
	 * to a <b>ndate</b> type value. <b>ndate</b> data type reprensents
	 * a date expressed as the number of days counted from <u>the epoch</u>.
	 * (see {@link java.util.Date})
	 * 
	 * @param date Date to be converted.
	 * 
	 * @return <b>ndate</b> value.
	 */
	public static int convertToNDate(String date) {

		/*
		 * Date format YYYYMMDD.
		 */
		AbsoluteDate aDate = new AbsoluteDate(date, AbsoluteDate.YMD);

		return aDate.diffDays(zeroDate);
	}

	private Scope() {
	}

	/**
	 * Constructs a scope instance from a String scope representation. It
	 * assumes that this representation is <b>completed</b>, ie, it will not be
	 * checked.
	 * 
	 * @param predicate String scope representation.
	 * 
	 * @see #Scope(Field[])
	 * @see #Scope(Field[], Operator[]) 
	 * @see #Scope(Field[], Operator[], Object[]) 
	 */
	public Scope(String predicate) {
		type = LEAF;
		completed = true;
		this.predicate = predicate;
	}

	/**
	 * Constructs a scope instance with the specified <code>fields</code>. The
	 * corresponding field values are left pending. Is assumes that the
	 * {@link Operator#EQ} operator must be applied for all specified
	 * <code>fields</code>.
	 * 
	 * @param fields Scope fields.
	 * 
	 * @see #Scope(String)
	 * @see #Scope(Field[], Operator[])
	 * @see #Scope(Field[], Operator[], Object[])
	 */
	public Scope(Field[] fields) {
		this(fields, eqs(fields.length));
	}

	/**
	 * Constructs a scope instance with the specified <code>fields</code> and
	 * <code>operators</code>. The corresponding field values are left pending.
	 * 
	 * @param fields Scope fields.
	 * 
	 * @param operators Scope operators. 
	 * 
	 * @see #Scope(String)
	 * @see #Scope(Field[])
	 * @see #Scope(Field[], Operator[], Object[])
	 */
	public Scope(Field[] fields, Operator[] operators) {
		this(fields, operators, null);
	}

	/**
	 * Constructs a scope instance with the specified <code>fields</code>,
	 * <code>operators</code> and <code>values</code>. If <code>values</code>
	 * is <code>null</code> then the corresponding field values are left
	 * pending.
	 * 
	 * @param fields Scope fields.
	 * 
	 * @param operators Scope operators.
	 * 
	 * @param values Scope values. 
	 * 
	 * @see #Scope(String)
	 * @see #Scope(Field[])
	 * @see #Scope(Field[], Operator[])
	 * 
	 * @throws IllegalArgumentException If few many fields were specified
	 *         or number of fields no match with number of operators or
	 *         number of values or value types no match with corresponding
	 *         field types.
	 */	
	public Scope(Field[] fields, Operator[] operators, Object[] values) {
		if (fields.length == 0) {
			throw new IllegalArgumentException("Few many fields.");
		}

		type = LEAF;
		this.fields = fields;

		setOperators(operators);

		if (values != null) {
			setValues(values);
		}
	}

	private void setOperators(Operator[] operators) {
		if (fields.length != operators.length) {
			throw new IllegalArgumentException(
					"Mismatch between #fields and #operators.");
		}

		this.operators = operators;
	}

	/**
	 * Sets <code>values</code> corresponding to scope fields. Scope is
	 * <b>completed</b>.
	 * 
	 * <p> It's equivalent to call to <code>setValues(values, "")</code>.
	 * </p>
	 * 
	 * @param values Scope values.
	 * 
	 * @throws IllegalArgumentException If few many fields were specified
	 *         or number of fields no match with number of operators or
	 *         number of values or value types no match with corresponding
	 *         field types.
	 */	
	public void setValues(Object[] values) {
		setValues(values, "");
	}

	/**
	 * Sets values corresponding to scope fields. Every field name
	 * is prefixed with <code>prefix</code> value. Scope is <b>completed</b>.
	 * 
	 * @param values Scope values.
	 * 
	 * @param prefix Scope fields prefix.
	 * 
	 * @throws IllegalArgumentException If few many fields were specified
	 *         or number of fields no match with number of operators or
	 *         number of values or value types no match with corresponding
	 *         field types.
	 */
	public void setValues(Object[] values, String prefix) {
		completed = false;
		predicate = "";
		offset = 0;

		this.values = values;

		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			Operator operator = operators[i];
			int type = field.getType();

			predicate +=
					prefix + field.getName() + " " + operator.symbol() + " ";

			if (operator == Operator.BETWEEN) {
				predicate += getOperand(type);
				predicate += " AND ";
				predicate += getOperand(type);
			} else if (operator == Operator.IN) {
				int operands = 0;
				String operand;

				predicate += "(";

				do {
					operand = getOperand(type, true);

					if (operand != null) {
						if (++operands > 1) {
							predicate += ", " + operand;
						} else {
							predicate += operand;
						}
					}
				} while (operand != null);

				predicate += ")";
			} else {
				predicate += getOperand(type);
			}

			if (i + 1 < fields.length) {
				predicate += " AND ";
			}
		}

		completed = true;
	}

	private String getOperand(int type) {
		return getOperand(type, false);
	}

	private String getOperand(int type, boolean nullable) {
		if (offset == values.length) {
			throw new IllegalArgumentException("Few many values");
		}

		int index = offset++;
		Object value = values[index];

		if (nullable && value == null) {
			return null;
		}

		switch (type) {
			case Field.TYPE_INT:
				if (value instanceof Integer) {
					return value.toString();
				}

				throw new IllegalArgumentException(
						"Integer value was expected to index " + index + ".");

			case Field.TYPE_DATE:
				if (value instanceof AbsoluteDate) {
					return "'" + value.toString() + "'";
				}

				throw new IllegalArgumentException(
						  "AbsoluteDate value was expected to "
						+ "index " + index + ".");

			case Field.TYPE_NDATE:
				if (value instanceof String) {
					String sValue = (String) value;

					/*
					 * Days Format #99999.
					 */
					try {
						if (sValue.length() <= 1 || sValue.charAt(0) != '#') {
							throw new Exception();
						}

						int days = Integer.parseInt(sValue.substring(1));

						if (days < 0) {
							throw new Exception();
						}

						return days + "";
					} catch (Exception e) {
						throw new IllegalArgumentException(
							  "Invalid days format for " + value + "'.");
					}
				}

				if (value instanceof Integer) {

					/*
					 * Period format YYYYMM.
					 */
					int period = ((Integer) value).intValue();
					int year = period / 100;
					int month = period % 100;

					if (year < 1200 || month < 1 || month > 12) {
						throw new IllegalArgumentException(
							  "Invalid period format for " + value + "'.");
					}

					AbsoluteDate aDate = new AbsoluteDate(1, month, year);

					return aDate.diffDays(zeroDate) + "";
				}

				if (value instanceof AbsoluteDate) {
					AbsoluteDate aDate = (AbsoluteDate) value;

					return aDate.diffDays(zeroDate) + "";
				}

				throw new IllegalArgumentException(
						"Invalid value '" + value + "' for type ndate.");

			case Field.TYPE_STRING:
				if (value instanceof String) {
					return "'" + value.toString() + "'";
				}

				throw new IllegalArgumentException(
						"String value was expected to index " + index + ".");

			default: // Field.TYPE_CHAR.
				if (value instanceof Character) {
					return "'" + value.toString() + "'";
				}

				throw new IllegalArgumentException(
						  "Character value was expected to "
						+ "index " + index + ".");
		}
	}

	/**
	 * Returns a string representation of the scope. Scope must be in
	 * <b>completed</b> state.
	 * 
	 * @return String representation.
	 */		
	public String toString() {
		checkComplete(this, 0);

		if (type == LEAF) {
			return predicate.trim();
		}

		if (type == AND) {
			return "(" + scope1 + ") AND (" + scope2 + ")";
		}

		if (type == OR) {
			return "(" + scope1 + ") OR (" + scope2 + ")";
		}

		return "NOT (" + scope1 + ")";
	}

	private static void checkComplete(Scope scope, int index) {

		if (!scope.completed) {
			String s = (index > 0) ? index + "" : "";

			throw new IllegalArgumentException("Uncompleted scope" + s + ".");
		}
	}

	private static Operator[] eqs(int length) {
		Operator[] operators = new Operator[length];

		for (int i = 0; i < operators.length; i++) {
			operators[i] = Operator.EQ;
		}

		return operators;
	}

	/**
	 * Constructs a completed scope instance applying <b>AND</b> logical
	 * operator between specified scopes. Both scopes must be in
	 * <b>completed</b> state.
	 * 
	 * @param scope1 First scope.
	 * 
	 * @param scope2 Second scope.
	 * 
	 * @return <code>scope1</code> AND <code>scope2</code>. 
	 */
	public static Scope and(Scope scope1, Scope scope2) {

		checkComplete(scope1, 1);
		checkComplete(scope2, 2);

		Scope iScope = new Scope();

		iScope.type = AND;
		iScope.scope1 = scope1;
		iScope.scope2 = scope2;
		iScope.completed = true;

		return iScope;
	}

	/**
	 * Constructs a completed scope instance applying <b>OR</b> logical operator
	 * between specified scopes. Both scopes must be in <b>completed</b> state.
	 * 
	 * @param scope1 First scope.
	 * 
	 * @param scope2 Second scope.
	 * 
	 * @return <code>scope1</code> OR <code>scope2</code>. 
	 */	
	public static Scope or(Scope scope1, Scope scope2) {
		checkComplete(scope1, 1);
		checkComplete(scope2, 2);

		Scope iScope = new Scope();

		iScope.type = OR;
		iScope.scope1 = scope1;
		iScope.scope2 = scope2;
		iScope.completed = true;

		return iScope;
	}

	/**
	 * Constructs a completed scope instance applying <b>NOT</b> logical
	 * operator to the specified scope. It must be in <b>completed</b> state.
	 * 
	 * @param scope Scope to be negated.
	 * 
	 * @return NOT <code>scope</code>. 
	 */	
	public static Scope not(Scope scope) {
		checkComplete(scope, 0);

		Scope iScope = new Scope();

		iScope.type = NOT;
		iScope.scope1 = scope;
		iScope.completed = true;

		return iScope;
	}

	/**
	 * Class demostration.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		Field[] fields = {
			new Field("FechaProceso", Field.TYPE_NDATE),
			new Field("NombreIsapre", Field.TYPE_STRING),
			new Field("RutEmpresa", Field.TYPE_INT),
			new Field("Convenio", Field.TYPE_INT)
		};

		Object[] values1 = {
			new Integer(200712),
					// new AbsoluteDate("2007-12-01", AbsoluteDate.YMD)
			"BANMEDICA",
			new Integer(70016160),
			new Integer(1)
		};

		Operator[] operators = {
			Operator.BETWEEN,
			Operator.LIKE,
			Operator.IN,
			Operator.EQ
		};

		Object[] values2 = {
			new Integer(200712),
			"#13940",
			"ISAPRE%",
			new Integer(70016160),
			new Integer(10450366),
			new Integer(9152041),
			null,
			new Integer(1)
		};

		/*
		 * fields   : "FechaProceso", "NombreIsapre", "RutEmpresa", "Convenio"
		 * operators: "=", "=", "=", "="
		 * values   : 200712, "BANMEDICA", 70016160, 1
		 */
		Scope scope1 = new Scope(fields);

		scope1.setValues(values1);
		System.out.println("\nscope1: " + scope1);

		/*
		 * fields   : "FechaProceso", "NombreIsapre", "RutEmpresa", "Convenio"
         * operators: "between", "like", "in", "="
         * values   : 200712, 200803, "ISAPRE%", 70016160, 10450366,
         *            9152041,null, 1
         */
		Scope scope2 = new Scope(fields, operators);

		scope2.setValues(values2);
		System.out.println("\nscope2 : " + scope2);

		Scope scope3;

		scope3 = Scope.and(scope1, scope2);
		System.out.println("\nscope1 AND scope2: " + scope3);

		scope3 = Scope.or(scope1, scope2);
		System.out.println("\nscope1 or scope2: " + scope3);

		scope3 = Scope.not(scope1);
		System.out.println("\nNOT scope1: " + scope3);

		Field[] fields4 = {
			new Field("NombreIsapre", Field.TYPE_STRING)
		};

		Operator[] operators4 = {
			Operator.IN
		};

		Object[] values4 = {
			"BANMEDICA",
			"NORMEDICA",
			"MASVIDA",
			null
		};

		Scope scope4 = new Scope(fields4, operators4);

		scope4.setValues(values4);
		System.out.println("\nscope4: " + scope4);

		System.out.println("\nscope all: |" + Scope.ALL + "|");

		if (args.length == 2) {
			if (args[0].equals("-sscope")) {
				Scope scope5 = new Scope(args[1]);

				System.out.println("\nscope5: " + scope5);
			} else if (args[0].equals("-ndate")) {
				String date = args[1];

				System.out.println(
						"\n" + date + " -> " + Scope.convertToNDate(date));
			}
		}
	}
}
