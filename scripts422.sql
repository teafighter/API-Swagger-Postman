Описание структуры: у каждого человека есть машина. Несколько человек могут пользоваться одной машиной. 
У каждого человека есть имя, возраст и признак того, что у него есть права (или их нет). 
У каждой машины есть марка, модель и стоимость. 
Также не забудьте добавить таблицам первичные ключи и связать их.
CREATE TABLE users (
    name TEXT NOT NULL PRIMARY KEY REFERENCES cars (model),
	age INTEGER NOT NULL CHECK (age > 0),
	car TEXT REFERENCES cars (model)
    license BOOLEAN  
);

CREATE TABLE cars (
	manufacturer TEXT NOT NULL,
    model TEXT NOT NULL PRIMARY KEY,
	price INTEGER NOT NULL  
);