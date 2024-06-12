#!/usr/bin/env bash

npm install
npm run build
npm run start

tail -f /dev/null