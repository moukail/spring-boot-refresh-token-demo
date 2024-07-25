#!/usr/bin/env bash

ng new \
--create-application=true \
--routing=true \
--style=scss \
--defaults \
--skip-git=true \
--skip-tests \
--interactive=false \
frontend  <<< 'N'

ng add @angular/material
ng add @ngrx/data@18.0.0-rc.1
#npm install @ngrx/data@18.0.0-rc.1 --save
npm install @auth0/angular-jwt

ng generate service services/Authentication
ng generate module manager
ng generate @angular/material:dashboard manager/Dashboard --module=manager