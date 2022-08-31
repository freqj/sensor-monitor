CREATE TABLE tp (id int8 generated by default as identity, name varchar(255), primary key (id));
CREATE TABLE unt (id int8 generated by default as identity, name varchar(255), primary key (id));
INSERT INTO public.tp(name)
	VALUES ('Pressure');
INSERT INTO public.tp(name)
	VALUES ('Voltage');
INSERT INTO public.tp(name)
	VALUES ('Temperature');
INSERT INTO public.tp(name)
	VALUES ('Humidity');
    INSERT INTO public.unt(name)
	VALUES ('°С');
INSERT INTO public.unt(name)
	VALUES ('bar');
INSERT INTO public.unt(name)
	VALUES ('voltage');
INSERT INTO public.unt(name)
	VALUES ('%');
