
-- create table emergency
create table emergency(
                          Id int primary key ,
                          Body text,
                          Published boolean
);

-- create table snippet
create table snippet(
                        Id int primary key ,
                        ClassificationId int not null,
                        OwnerId int not null,
                        FileOwnerId int not null,
                        TopicId int not null,
                        Title varchar(255),
                        Body text,
                        Url varchar(255),
                        Published boolean
);

-- create table classification
create table Classification(
                               Id int primary key,
                               Title varchar(255),
                               Published boolean
);

-- create table Owner
create table Owner(
                      Id int primary key ,
                      Title varchar(255),
                      Published boolean
);

-- create table Topic
create table Topic(
                      Id int primary key ,
                      Title varchar(255),
                      Color varchar(20),
                      Published boolean
);

-- create table User
create table User(
    Id int primary key
);

-- create table UserFavoriteSnippet
create table UserFavoriteSnippet(
                                    Id int primary key ,
                                    UserId int not null,
                                    SnippetId int not null
);

-- create Constraints for table UserFavoriteSnippet
alter table UserFavoriteSnippet add foreign Key (UserId) references User(Id);
alter table UserFavoriteSnippet add foreign Key (SnippetId) references snippet(Id);
-- create constraints for table Snippet
alter table snippet add constraint fk_snippet_UserFavoriteSnippet foreign Key (Id) references UserFavoriteSnippet(SnippetId);
alter table snippet add constraint fk_snippet_Topic foreign Key (TopicId) references Topic(Id);
alter table snippet add constraint fk_snippet_Owner foreign Key (OwnerId) references Owner(Id);
alter table snippet add constraint fk_snippet_Classification foreign Key (ClassificationId) references Classification(Id);