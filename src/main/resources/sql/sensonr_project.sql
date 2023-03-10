create TABLE Sensor
(
    id   int primary key generated by default as identity,
    name varchar(30) not null unique
);

create TABLE Measurement
(
    id   int primary key generated by default as identity,
    value double precision not null ,
    raining bool not null ,
    sensor varchar(30) references Sensor(name)  not null ,
    measurement_date timestamp not null
);