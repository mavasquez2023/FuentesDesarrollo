

public class PDFDrawerTester {

	StringBuffer sb = new StringBuffer();

	public PDFDrawerTester() {
	}

	public static void usage() {
		System.err.println(
				  "usage:\n"
				+ "    rectangle <x> <y> <width> <height> <thickness>\n"
				+ "    table <x> <y> <width> <height> <cols> <rows> "
				+ "<headerHeight> <thickness>\n");

		System.exit(1);
	}

	public static void main(String[] args) {
		PDFDrawerTester tester = new PDFDrawerTester();

		if (args.length == 0) {
			usage();
		}

		for (int i = 0; i < args.length; i++) {
			String figure = args[i];

			if (figure.equals("rectangle")) {
				if (i + 5 >= args.length) {
					usage();
				}

				int x = Integer.parseInt(args[i + 1]);
				int y = Integer.parseInt(args[i + 2]);
				int width = Integer.parseInt(args[i + 3]);
				int height = Integer.parseInt(args[i + 4]);
				int thickness = Integer.parseInt(args[i + 5]);

				i += 5;

				tester.rectangle(x, y, width, height, thickness);
			} else if (figure.equals("table")) {
				if (i + 8 >= args.length) {
					usage();
				}

				int x = Integer.parseInt(args[i + 1]);
				int y = Integer.parseInt(args[i + 2]);
				int width = Integer.parseInt(args[i + 3]);
				int height = Integer.parseInt(args[i + 4]);
				int cols = Integer.parseInt(args[i + 5]);
				int rows = Integer.parseInt(args[i + 6]);
				int headerHeight = Integer.parseInt(args[i + 7]);
				int thickness = Integer.parseInt(args[i + 8]);

				i += 8;

				tester.table(
						x,
						y,
						width,
						height,
						cols,
						rows,
						headerHeight,
						thickness);
			}
		}

		System.out.print(tester.getContent());
	}

	public void table(int x, int y, int width, int height, int cols, int rows,
			int headerHeight, int thickness) {

		writeln(
				  "% table x=" + x + " y=" + y
				+ " width=" + width + " height=" + height
				+ " cols=" + cols + " rows=" + rows
				+ " headerHeight=" + headerHeight + " thickness=" + thickness);

		rectangle(x, y, width, height + headerHeight, thickness);

		if (headerHeight > 0 ) {
			outline(x, y - headerHeight, width, thickness);
		}

		int dx = width / cols;

		int y0 = y - (height + headerHeight);
		int x0 = x;
		int dth = thickness / 2;

		for (int col = 0; col < cols - 1; col++) {
			x0 += dx;

			outline(x0 - dth, y0, thickness, height);
		}

		int dy = height / rows;

		y0 = y - headerHeight;
		dth = thickness / 2;

		for (int row = 0; row < rows - 1; row++) {
			y0 -= dy;

			outline(x, y0 - dth, width, thickness);
		}
	}

	public void rectangle(int x, int y, int width, int height, int thickness) {
		writeln(
				  "% rectangle x=" + x + " y=" + y
				+ " width=" + width + " height=" + height
				+ " thickness=" + thickness);

		outline(x, y, width, thickness);
		outline(x + width - thickness, y - height, thickness, height);
		outline(x, y - height, width, thickness);
		outline(x, y - height, thickness, height);
	}

	public void line(int x, int y, int length, int thickness) {
		writeln(
				  "% x=" + x + " y=" + y
				+ " length=" + length + " thickness=" + thickness + "\n");

		outline(x, y, length, thickness);
	}

	public String getContent() {
		return sb.toString();
	}

	private void outline(int x, int y, int length, int height) {
		sb.append(
				  "q\n"
				+ y + " " + x + " " + height + " " + length + " re\n"
				+ "f*\n"
				+ "Q\n"
				+ "\n");
	}

	/* UNUSED.
	private void writeln() {
		writeln("");
	}
	*/

	private void writeln(String text) {
		write(text + "\n");
	}

	private void write(String text) {
		sb.append(text);
	}
}
