cd ..

docker build --platform linux/arm64 -t smart-cctv:latest -f ./docker/Dockerfile-arm .
pause