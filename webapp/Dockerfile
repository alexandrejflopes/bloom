FROM node:latest



# set working directory
WORKDIR /app

COPY package.json /app/package.json
COPY ./build /app/build
COPY ./src /app/src
COPY ./public /app/public

RUN npm install npm@latest
RUN npm install

ENV PATH /app/node_modules/.bin:$PATH


# port in the Runtime VM
EXPOSE 3000

# start app
CMD ["npm", "start"]