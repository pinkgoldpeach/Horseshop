DROP TABLE pferd;
DROP TABLE reiter;
DROP TABLE rennen;

CREATE TABLE pferd (
  id INTEGER IDENTITY PRIMARY KEY ,
  speedMin  INTEGER,
  speedMax INTEGER,
  deleted BOOLEAN NOT NULL DEFAULT FALSE ,
  foto VARCHAR(100),
  name VARCHAR(100) ,
  CHECK (50 <= speedMin AND speedMin <= speedMax AND speedMax <= 100),
);

CREATE TABLE reiter (
  id INTEGER IDENTITY PRIMARY KEY,
  skill DOUBLE DEFAULT 0,
  deleted BOOLEAN NOT NULL DEFAULT FALSE,
  name VARCHAR (100),
);

CREATE TABLE rennen (
    rID INTEGER  REFERENCES reiter(id),
    pID INTEGER REFERENCES pferd(id),
    id INTEGER,
    rank INTEGER,
    luck DOUBLE,
    skill DOUBLE,
    tempo DOUBLE,
    ergebnis DOUBLE NOT NULL ,
    PRIMARY KEY (rID, pID, id),
);

INSERT INTO pferd(speedMin, speedMax, name) VALUES (50,100, 'Spirit');
INSERT INTO pferd(speedMin, speedMax, name) VALUES (55, 95, 'Kincsem');
INSERT INTO reiter(name) VALUES ('Olive Snook');
INSERT INTO  reiter(name) VALUES ('Doctor Who');
INSERT INTO rennen(rID, pID, id, ergebnis) VALUES (1,1, 0, 5);
INSERT INTO rennen(rID, pID, id, ergebnis) VALUES (2,2,0,6);


