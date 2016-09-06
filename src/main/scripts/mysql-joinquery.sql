CREATE VIEW show_presentation AS
	SELECT S.show_id, M.title, S.show_date, S.show_time
		FROM show_table S 
		JOIN movie M ON S.movie_id = M.movie_id