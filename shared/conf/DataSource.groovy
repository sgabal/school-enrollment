//dataSource {
//	dbCreate="create"
//	url="jdbc:oracle:thin:@192.168.56.101:1521:xe"
//	username="schoolportal"
//	password="schoolportal"
//	driverClassName = "oracle.jdbc.driver.OracleDriver"
//	dialect="org.hibernate.dialect.Oracle10gDialect"
//	loggingSql = false
//	pooled = true
//	properties {
//		maxActive = 50
//		maxIdle = 25
//		minIdle = 5
//		initialSize = 5
//		minEvictableIdleTimeMillis = 60000
//		timeBetweenEvictionRunsMillis = 60000
//		maxWait = 10000
//		validationQuery = "select 1 from dual"
//	}
//}

dataSource {
    dbCreate = "create" //"create-drop" // one of 'create', 'create-drop','update'
    driverClassName = "com.mysql.jdbc.Driver"
    url = "jdbc:mysql://localhost/school-enrollment?autoReconnect=true"
    username = "admin"
    password = "admin"
}



