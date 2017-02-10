export JPDA_ADDRESS=8000
export JPDA_TRANSPORT=dt_socket
#$CATALINA_HOME/bin/shutdown.sh
rm -rf /home/adriabe/Documents/webapps/*.war
#rm -rf /home/adriabe/Documents/webapps/adriabe
svn up
mvn clean install -Dmaven.test.skip=true -DdownloadSources=true
mv target/Adriabe.war  /home/adriabe/Documents/webapps/adriabe.war
#$CATALINA_HOME/bin/startup.sh
