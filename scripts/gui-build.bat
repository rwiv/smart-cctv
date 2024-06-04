cd ..

rmdir /s /q .\src\main\resources\dist
cd .\smart-cctv-front
pnpm build:prod && move .\dist ..\src\main\resources
pause