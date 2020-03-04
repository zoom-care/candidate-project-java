create table employee (
  id bigint(20) auto_increment,
  supervisor_id bigint(20),
  primary key (id),
  foreign key (supervisor_id) references employee(id)
);

create table property (
  employee_id bigint(20),
  key varchar(256),
  value varchar(256),
  primary key (employee_id, key),
  foreign key (employee_id) references employee(id)
);