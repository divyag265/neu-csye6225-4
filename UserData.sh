#!/bin/bash
sudo apt-get update
sudo apt-get -y install python-software-properties
curl -sL https://deb.nodesource.com/setup_7.x | sudo -E bash -
sudo apt-get -y install nodejs
sudo npm install -g nodemon
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv EA312927
echo "deb http://repo.mongodb.org/apt/ubuntu "$(lsb_release -sc)"/mongodb-org/3.2 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.2.list
sudo apt-get update
sudo apt-get install -y mongodb-org
sudo sh -c "echo '[Unit]
Description=High-performance, schema-free document-oriented database
After=network.target

[Service]
User=mongodb
ExecStart=/usr/bin/mongod --quiet --config /etc/mongod.conf

[Install]
WantedBy=multi-user.target' >> /etc/systemd/system/mongodb.service"
sudo systemctl start mongodb
sudo ufw disable
sudo mkdir CLOUD\ PROJECT
cd CLOUD\ PROJECT
sudo git init
sudo git clone https://divyag265:cloudteam6@github.com/divyag265/neu-csye6225-4.git
cd neu-csye6225-4
sudo git clone https://github.com/divyag265/ssl.git
