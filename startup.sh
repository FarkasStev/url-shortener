cp target/url-shortener.war tomcat/webapps/url-shortener.war
./tomcat/bin/startup.sh
tail -f tomcat/logs/catalina.out