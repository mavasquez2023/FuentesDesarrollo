package cl.araucana.cp.receipt;

public class AcceptedFile 
{
	private String id;
	private String fileName;
	
	private int size;
	private int compressedSize;
	private int nRecords;

	public AcceptedFile() {
	}

	public AcceptedFile(String id, String fileName, int size) {
		this(id, fileName, size, 0, 0);
	}

	public AcceptedFile(String id, String fileName, int size, int nRecords) {
		this(id, fileName, size, 0, nRecords);
	}

	public AcceptedFile(String id, String fileName, int size, int compressedSize, int nRecords) 
	{
		setID(id);
		setFileName(fileName);
		setSize(size);
		setCompressedSize(compressedSize);
		setNRecords(nRecords);
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return this.fileName;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public void setCompressedSize(int compressedSize) {
		this.compressedSize = compressedSize;
	}

	public int getCompressedSize() {
		return this.compressedSize;
	}

	public void setNRecords(int nRecords) {
		this.nRecords = nRecords;
	}

	public int getNRecords() {
		return this.nRecords;
	}

	public String getComment() {
		String comment = "size=" + sizeToString(this.size);

		if (this.compressedSize > 0) 
		{
			int rate = (int) (100.0 * (this.size - this.compressedSize) / this.size);

			comment +=
					  " compressed=" + sizeToString(this.compressedSize)
					+ " compression rate=" + rate + "%";
		}

		if (this.nRecords > 0)
			comment += " records=" + this.nRecords;

		return comment;
	}

	private static String sizeToString(int size) {
		final int ONE_KILOBYTE = 1024;
		final int ONE_MEGABYTE = 1048576;

		if (size < ONE_KILOBYTE) {
			return size + "bytes";
		}

		if (size < ONE_MEGABYTE) {
			return ((int) (1.0 * size / ONE_KILOBYTE + 0.5)) + "KB";
		}

		return ((int) (1.0 * size / ONE_MEGABYTE + 0.5)) + "MB";
	}
}
