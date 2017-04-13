package eu.suhajko.movie.scanner;

import eu.suhajko.movie.Movie;
import eu.suhajko.movie.movieplayer.exception.MovieWebException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Created by marek.melis on 4/12/17.
 */
public class MovieScannerImpl implements MovieScanner {

    Pattern pattern = Pattern.compile("\\.(avi|mkv|mp4)$");

    Logger logger = LoggerFactory.getLogger(getClass());


    @Override public Set<Movie> scanForMovies(File directory) {
        if (!directory.isDirectory()) {
            throw new MovieWebException(String.format("%s is not a directory", directory.getName()));
        }

        return findMovieFiles(directory).stream()
                .map(f -> new Movie()
                        .setTitle(f.getName())
                        .setDescription(f.getName())
                        .setHash(MD5CheckSum.getMD5Checksum(f))
                        .setPathToMovie(f.getAbsolutePath()))
                .collect(Collectors.toSet());
    }

    private List<File> findMovieFiles(File directory) {

        Path path = Paths.get(directory.toURI());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {


            List<File> movieFiles = new ArrayList<>();

            stream.forEach(p -> {
                File file = p.toFile();
                Matcher matcher = pattern.matcher(file.getName());

                if (matcher.find()) {
                    movieFiles.add(file);
                }
            });
            return movieFiles;
        }
        catch (IOException e) {
            logger.error("Error while searching directory for movies {}", directory, e);
            throw new MovieWebException("Error while searching directory for movies ");
        }


    }
}
