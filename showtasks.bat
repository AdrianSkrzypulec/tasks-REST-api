call runcrud
if "%ERRORLEVEL%" == "0" goto finish
echo.
echo run script error

:finish
start http://localhost:8080/crud/v1/task/getTasks
echo.
echo Crud started sucess! :)