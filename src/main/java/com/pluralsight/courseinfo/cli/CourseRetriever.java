package com.pluralsight.courseinfo.cli;

import com.pluralsight.courseinfo.cli.service.CourseRetrievalService;
import com.pluralsight.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {
    private static final Logger log = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String... args) {
        log.info("Course Level");
        if (args.length == 0) {
            log.warn("Please provide an authour name as first argument");
            return;
        }

        try {
            // if the system is not given any variable to start with
            retrieveCourses(args[0]);
        } catch (Exception e) {
            // then throw an error
            log.error("Unexpected error", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        String authorIdOfSanderMak = "sander-mak";
        log.info("Retrieving courses for author '{}'", authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCoursesForAuthor(authorIdOfSanderMak)
                .stream()
                .filter(not(PluralsightCourse::isRetired))
                .toList();
        log.info("Retrieved the following {} courses: {}", coursesToStore.size(), coursesToStore);
    }
}
