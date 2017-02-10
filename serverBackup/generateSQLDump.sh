DAY=`/bin/date +%Y%m%d%H`
DATABASE_NAME=billing
USER=root
PASSWORD=8bo5nel3
BACKUP_PATH="/home/adriabe/Documents/BackupDatabase/"
BACKUP_FILENAME="adriabe-celina.$DAY.sql"
BACKUP_FILE=$BACKUP_PATH$BACKUP_FILENAME
BACKUP_ERROR_FILE=$BACKUP_PATH"error$DAY.err"
SENDER_EMAIL=alejandro.mildiner@gmail.com
SENDER_EMAIL_PASSWORD=8bo5nel5
RECIPIENT_EMAIL=mildiner.sergio@gmail.com,alejandro.mildiner@gmail.com
SUBJECT="Database Backup Failed"
MESSAGE="Check error file: error$DAY.err"
mysqldump $DATABASE_NAME -u $USER -p$PASSWORD > $BACKUP_FILE 2>$BACKUP_ERROR_FILE 
if [ "$?" -eq 0 ]
then
    find /home/adriabe/Documents/BackupDatabase/*.sql -mtime +10 -type f -delete
else
    sendemail -f $SENDER_EMAIL -t $RECIPIENT_EMAIL -u $SUBJECT -m $MESSAGE -s smtp.gmail.com:587 -o tls=yes -xu $SENDER_EMAIL -xp $SENDER_EMAIL_PASSWORD
fi
