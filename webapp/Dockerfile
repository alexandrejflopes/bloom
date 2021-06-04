FROM node:14-alpine

# set working directory
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH

COPY package.json /app/package.json
COPY package-lock.json /app/package-lock.json
COPY ./src /app/src
COPY ./public /app/public
#COPY . /app

RUN CI=true

RUN npm install npm@latest
RUN npm install



# port in the Runtime VM
EXPOSE 3000

# start app
CMD ["npm", "start"]