db = db.getSiblingDB('traceability'); // Cambia a tu base de datos

db.createUser({
  user: 'app_user',
  pwd: 'app_password',
  roles: [
    {
      role: 'readWrite',
      db: 'traceability',
    },
  ],
});