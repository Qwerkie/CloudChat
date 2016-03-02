CREATE TABLE Credentials (
    UserID VARCHAR(25) not null primary key,
    Password VARCHAR(25),
    LoggedOn BOOLEAN
);
CREATE TABLE Messages (
    MessageID TIMESTAMP not null primary key,
    UserID VARCHAR(25), -- not sure if this should be a foreign key or not
    Message VARCHAR(5000),
    Category VARCHAR(25)
);
INSERT INTO Credentials VALUES ('ctd5100', 'asianman', false);