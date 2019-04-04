create table node (
  id bigint(20) not null auto_increment,
  parent_node_id bigint(20),
  primary key (id),
  foreign key (parent_node_id) references node(id)
);

create table property (
  id bigint(20) not null auto_increment,
  node_id bigint(20) not null,
  key varchar(256),
  value varchar(256),
  primary key (id),
  foreign key (node_id) references node(id)
);