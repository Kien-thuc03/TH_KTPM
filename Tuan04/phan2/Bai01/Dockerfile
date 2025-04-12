FROM node:18

WORKDIR /usr/src/app

#copy package.json and package-lock.json
COPY package*.json ./

RUN npm install

COPY . .

#port
EXPOSE 3000

CMD ["node", "app.js"]