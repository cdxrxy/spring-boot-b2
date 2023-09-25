@echo off

SET URL=http://localhost:8080
SET ENDPOINT=/items

curl -sS -f "%URL%%ENDPOINT%" > NUL

if %ERRORLEVEL% NEQ 0 echo GET ITEMS FAILED & PAUSE & exit 1

echo GET ITEMS: SUCCESS

SET ENDPOINT=/login

curl -sS -f -H "Content-Type: application/json" -d "{\"username\": \"cdxray\", \"password\": \"123\"}" "%URL%%ENDPOINT%" > token.txt

if %ERRORLEVEL% NEQ 0 echo LOGIN FAILED & DEL token.txt & PAUSE & exit 1

echo LOGIN: SUCCESS

SET TOKEN=

FOR /F %%i IN (token.txt) DO SET TOKEN=%TOKEN%%%i

SET AUTH_HEADER=Authorization: Bearer %TOKEN%

DEL token.txt

SET ENDPOINT=/orders

curl -sS -f -H "%AUTH_HEADER%" "%URL%%ENDPOINT%" > NUL

if %ERRORLEVEL% NEQ 0 echo GET ORDERS FAILED & PAUSE & exit 1

echo GET ORDERS: SUCCESS

SET ENDPOINT=/users

curl -sS -f -H "%AUTH_HEADER%" "%URL%%ENDPOINT%" > NUL

if %ERRORLEVEL% NEQ 0 echo GET USERS FAILED & PAUSE & exit 1

echo GET USERS: SUCCESS

echo SMOKE TEST SUCCESSFULLY PASSED

PAUSE