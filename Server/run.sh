export JPDA_ADDRESS=8000
export JPDA_TRANSPORT=dt_socket
/Library/Tomcat/bin/shutdown.sh
rm -rf /Library/Tomcat/webapps/*.war
rm -rf /Library/Tomcat/webapps/Adriabe
mvn clean install -Dmaven.test.skip=true -DdownloadSources=true
cd target
mv Adriabe.war  /Library/Tomcat/webapps/
rm -rf /Library/Tomcat/logs/*.log
rm -rf /Library/Tomcat/logs/*.out
rm -rf /Library/Tomcat/logs/*.txt
/Library/Tomcat/bin/catalina.sh jpda start
cd ..