cp target/url-shortener.war tomcat/wepapps/url-shortener.war
./tomcat/bin/startup.sh
tail -f tomcat/logs/catalina.out