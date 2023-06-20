package cl.laaraucana.mandatocesantia.reportDao;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.stereotype.Service;

@Service
public class ReportDaoImpl implements ReportDao{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Connection getConnection() throws Exception {

		Connection connection = dataSource.getConnection();
		return connection;
	}


}
