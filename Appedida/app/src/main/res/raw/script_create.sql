CREATE TABLE if not exists "android_metadata" ("locale" TEXT DEFAULT 'en_US');
CREATE TABLE if not exists "user" ("_id" INTEGER PRIMARY KEY ,"login" TEXT, "senha" TEXT, "email" TEXT,"cpf" TEXT);
CREATE TABLE if not exists "pedido" ("_id" INTEGER PRIMARY KEY ,"idsProdutos" TEXT, "valor_Pedido" TEXT);



