## env files

- `docker/.env`
- `src/resources/application-secret.yml` (dev only)

## Run App

```shell
docker compose \
  -f ./docker/docker-compose.yml \
  --env-file ./docker/.env up
```

## Related Repositories

- [smart-cctv-front](https://github.com/rwiv/smart-cctv-front)
- [smart-cctv-camera](https://github.com/rwiv/smart-cctv-camera)

