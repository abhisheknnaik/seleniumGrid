set projectLocation=
cd %projectLocation%

mvn clean test -Dplateform=Mac -Dbrowser=firefox -Dversion=40

pause