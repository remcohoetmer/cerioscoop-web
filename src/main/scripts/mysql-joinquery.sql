CREATE VIEW show_presentation AS 
	SELECT S.show_id, M.title, R.room_name, S.show_date, S.show_time, R.chair_amount, M.trailer
		FROM `show_table` S 
		JOIN movie M ON S.movie_id = M.movie_id
		JOIN room R ON R.room_id = S.room_id