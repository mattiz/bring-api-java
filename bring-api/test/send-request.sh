#!/bin/sh

request=`cat bookingrequest.xml | sed ':a;N;$!ba;s/\n//g'`

curl -v -X POST \
-H "X-MyBring-API-Uid: mathias@verida.no" \
-H "X-MyBring-API-Key: 8872e4dc-d895-4e66-a2d4-0c4b51b6ff02" \
-H "Content-Type: application/xml" \
-H "Accept: application/xml" \
-d "$request" \
"https://www.mybring.com/booking/api/booking" \
| xmlstarlet fo \
| source-highlight -s xml -f esc --binary-output
