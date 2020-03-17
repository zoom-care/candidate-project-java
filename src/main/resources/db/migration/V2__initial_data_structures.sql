insert into employee values ();
select @ceo := scope_identity();
insert into property (employeeId, key, value) values (@ceo, 'value', 'CEO');

insert into employee (supervisorId) values (@ceo);
select @vpsales := scope_identity();
insert into property (employeeId, key, value) values (@vpsales, 'value', 'Vice President of Sales');

insert into employee(supervisorId) values (@vpsales);
select @nasales := scope_identity();
insert into property (employeeId, key, value) values (@nasales, 'value', 'Regional Director of Sales');
insert into property (employeeId, key, value) values (@nasales, 'region', 'North America');

insert into employee(supervisorId) values (@nasales);
select @salesrep1 := scope_identity();
insert into property (employeeId, key, value) values (@salesrep1, 'value', 'Sales Representative');

insert into employee(supervisorId) values (@vpsales);
select @eusales := scope_identity();
insert into property (employeeId, key, value) values (@eusales, 'value', 'Regional Director of Sales');
insert into property (employeeId, key, value) values (@eusales, 'region', 'Europe');

insert into employee (supervisorId) values (@ceo);
select @vpp := scope_identity();
insert into property (employeeId, key, value) values (@vpp, 'value', 'Vice President of People');

insert into employee (supervisorId) values (@ceo);
select @vpm := scope_identity();
insert into property (employeeId, key, value) values (@vpm, 'value', 'Vice President of Marketing');

insert into employee(supervisorId) values (@vpm);
select @namarketing := scope_identity();
insert into property (employeeId, key, value) values (@namarketing, 'value', 'Regional Director of Marketing');
insert into property (employeeId, key, value) values (@namarketing, 'region', 'North America');

insert into employee(supervisorId) values (@vpm);
select @eumarketing := scope_identity();
insert into property (employeeId, key, value) values (@eumarketing, 'value', 'Regional Director of Marketing');
insert into property (employeeId, key, value) values (@eumarketing, 'region', 'Europe');