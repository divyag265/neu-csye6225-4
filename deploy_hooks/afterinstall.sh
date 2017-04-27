#!/bin/bash
for var in $(sudo lsof -ti :443)
do
  sudo kill -9 $var
done
sudo nohup nodemon
