#!/usr/bin/env bash

npm outdated
npm install
npm list
npm run build
npm run start

tail -f /dev/null