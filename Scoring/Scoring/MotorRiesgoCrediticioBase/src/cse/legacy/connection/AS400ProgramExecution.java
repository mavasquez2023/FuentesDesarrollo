package cse.legacy.connection;

public interface AS400ProgramExecution {

	public AS400ProgramExecutionResult execute(String rut, String digito)
			throws AS400ProgramExecutionException;

}