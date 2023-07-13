FROM node:16 as client
WORKDIR /usr/src/app/client
COPY client/package.json package.json
COPY client/package-lock.json package-lock.json
RUN npm ci
COPY ./client/public public
COPY ./client/src src
RUN npm run build

FROM node:16 as app
WORKDIR /usr/src/app
COPY package.json package.json
COPY package-lock.json package-lock.json
RUN npm ci
COPY .babelrc .babelrc
COPY .env .env
COPY ./server server
COPY --from=client /usr/src/app/client/build client/build
CMD ["npm", "run", "start"]

