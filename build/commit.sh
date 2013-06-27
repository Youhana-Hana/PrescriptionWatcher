set -e

cd ./src

echo 'building project'
/usr/dev/android-sdk-linux/tools/android update project -p .
ant clean release

echo 'running android lint'
/usr/dev/android-sdk-linux/tools/lint --disable Wakelock -Werror --exitcode .

echo 'running tests'
cd ../test
ant build-project test
