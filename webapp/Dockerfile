FROM node:latest
ENV PATH /app/node_modules/.bin:$PATH

# set working directory
WORKDIR /app

COPY ["package.json", "package-lock.json", "./"]
COPY ./src /app/src
COPY ./public /app/public

RUN npm install

# port in the Runtime VM
EXPOSE 50030

# start app
CMD ["npm", "start"]