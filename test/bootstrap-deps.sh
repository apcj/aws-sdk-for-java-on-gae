#!/bin/bash -e

ALREADY_BOOTSTRAPPED=`ls war/WEB-INF/lib/gae-aws-sdk* | wc -l`
if [ $ALREADY_BOOTSTRAPPED != "0" ]
then
  echo "Already bootstrapped!"
  exit 1
fi

cd war/WEB-INF/lib
for dep in `ls ../../../../third-party/**/*.jar`
do
  if [[ $dep != *appengine-api-1.0-sdk* ]]
  then
    ln -s $dep .
  fi
done
ln -s ../../../../build/gae-aws-sdk-*.jar .
echo "Done bootstrapping."
