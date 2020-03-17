create table employee (
  id bigint(20) not null auto_increment,
  supervisorId bigint(20),
  primary key (id),
  foreign key (supervisorId) references employee(id)
);

create table property (
  employeeId bigint(20) not null,
  key varchar(256),
  value varchar(256),
  primary key (employeeId, key),
  foreign key (employeeId) references employee(id)
);