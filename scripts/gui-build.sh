#! /bin/bash

cd ..

rm -rf ./src/main/resources/dist
cd ./smart-cctv-front
pnpm build:prod && mv ./dist ../src/main/resources