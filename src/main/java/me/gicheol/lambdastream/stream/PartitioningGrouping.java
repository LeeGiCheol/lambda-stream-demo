package me.gicheol.lambdastream.stream;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/*
    그룹화와 분할
    partitioningBy()는 스트림을 2분할 한다.
    groupingBy()는 스트림을 n분할 한다.
 */
public class PartitioningGrouping {

    public static void main(String[] args) {
        StudentPartitioningGrouping[] stuArr = createStudents();

        partitioning(stuArr);
        grouping(stuArr);
    }


    private static void partitioning(StudentPartitioningGrouping[] stuArr) {
        System.out.printf("1. 단순분할 (성별로 분할) %n");
        Map<Boolean, List<StudentPartitioningGrouping>> stuByGender = Stream.of(stuArr)
                .collect(partitioningBy(StudentPartitioningGrouping::isMale));

        List<StudentPartitioningGrouping> maleStudent = stuByGender.get(true);
        List<StudentPartitioningGrouping> feMaleStudent = stuByGender.get(false);

        for (StudentPartitioningGrouping student : maleStudent) {
            System.out.println("male student = " + student);
        }

        for (StudentPartitioningGrouping student : feMaleStudent) {
            System.out.println("female student = " + student);
        }

        System.out.printf("%n2. 단순분할 + 통계 (성별 학생 수)%n");

        Map<Boolean, Long> stuNumByGender = Stream.of(stuArr)
                .collect(partitioningBy(StudentPartitioningGrouping::isMale, counting()));

        System.out.println("남학생 수 = " + stuNumByGender.get(true));
        System.out.println("여학생 수 = " + stuNumByGender.get(false));

        System.out.printf("%n3. 단순분할 + 통계 (성별 1등)%n");
        Map<Boolean, Optional<StudentPartitioningGrouping>> topScoreByGender = Stream.of(stuArr)
                .collect(partitioningBy(StudentPartitioningGrouping::isMale,
                        maxBy(Comparator.comparingInt(StudentPartitioningGrouping::getScore))));

        System.out.println("남학생 1등 = " + topScoreByGender.get(true));
        System.out.println("여학생 1등 = " + topScoreByGender.get(false));

        Map<Boolean, StudentPartitioningGrouping> topScoreByGender2 = Stream.of(stuArr)
                .collect(partitioningBy(StudentPartitioningGrouping::isMale,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(StudentPartitioningGrouping::getScore)),
                                Optional::get)));

        System.out.println("남학생 1등 = " + topScoreByGender2.get(true));
        System.out.println("여학생 1등 = " + topScoreByGender2.get(false));

        System.out.printf("%n4. 다중분할 (성별 불합격자, 100점 이하)%n");

        Map<Boolean, Map<Boolean, List<StudentPartitioningGrouping>>> failedStuByGender = Stream.of(stuArr)
                .collect(partitioningBy(StudentPartitioningGrouping::isMale,
                        partitioningBy(s -> s.getScore() <= 100)));

        List<StudentPartitioningGrouping> failedMaleStu = failedStuByGender.get(true).get(true);
        List<StudentPartitioningGrouping> failedFemaleStu = failedStuByGender.get(false).get(true);

        for (StudentPartitioningGrouping studentPartitioningGrouping : failedMaleStu) {
            System.out.println(studentPartitioningGrouping);
        }

        for (StudentPartitioningGrouping studentPartitioningGrouping : failedFemaleStu) {
            System.out.println(studentPartitioningGrouping);
        }
    }




    private static void grouping(StudentPartitioningGrouping[] stuArr) {
        System.out.printf("1. 단순그룹화 (반별로 그룹화)%n");

        Map<Integer, List<StudentPartitioningGrouping>> stuByBan = Stream.of(stuArr)
                .collect(groupingBy(StudentPartitioningGrouping::getBan));

        for (List<StudentPartitioningGrouping> value : stuByBan.values()) {
            for (StudentPartitioningGrouping s : value) {
                System.out.println(s);
            }
        }

        System.out.printf("%n2. 단순그룹화 (성적별로 그룹화)%n");

        Map<StudentPartitioningGrouping.Level, List<StudentPartitioningGrouping>> stuByLevel = Stream.of(stuArr)
                .collect(groupingBy(s -> {
                    if (s.getScore() >= 200) {
                        return StudentPartitioningGrouping.Level.HIGH;
                    } else if (s.getScore() >= 100) {
                        return StudentPartitioningGrouping.Level.MID;
                    } else {
                        return StudentPartitioningGrouping.Level.LOW;
                    }
                }));

        TreeSet<StudentPartitioningGrouping.Level> keyset = new TreeSet<>(stuByLevel.keySet());

        for (StudentPartitioningGrouping.Level key : keyset) {
            System.out.println("[" + key + "]");

            for (StudentPartitioningGrouping s : stuByLevel.get(key)) {
                System.out.println(s);
            }
            System.out.println();
        }

        System.out.printf("%n3. 단순그룹화 + 통계 (성적별 학생수)%n");

        Map<StudentPartitioningGrouping.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(groupingBy(s -> {
                    if (s.getScore() >= 200) {
                        return StudentPartitioningGrouping.Level.HIGH;
                    } else if (s.getScore() >= 100) {
                        return StudentPartitioningGrouping.Level.MID;
                    } else {
                        return StudentPartitioningGrouping.Level.LOW;
                    }
                }, counting()));

        for (StudentPartitioningGrouping.Level key : stuCntByLevel.keySet()) {
            System.out.printf("[%s] - %d명, ", key, stuCntByLevel.get(key));
        }
        System.out.println();

        System.out.printf("%n4. 다중그룹화 (학년별, 반별)");

        Map<Integer, Map<Integer, List<StudentPartitioningGrouping>>> stuByGradeAndClass = Stream.of(stuArr)
                .collect(groupingBy(StudentPartitioningGrouping::getGrade,
                        groupingBy(StudentPartitioningGrouping::getBan)));

        for (Map<Integer, List<StudentPartitioningGrouping>> grade : stuByGradeAndClass.values()) {
            for (List<StudentPartitioningGrouping> stuClass : grade.values()) {
                System.out.println();
                for (StudentPartitioningGrouping s : stuClass) {
                    System.out.println(s);
                }
            }
        }

        System.out.printf("%n5. 다중그룹화 + 통계 (학년별, 반별 1등)%n");

        Map<Integer, Map<Integer, StudentPartitioningGrouping>> topStuByGradeAndClass = Stream.of(stuArr)
                .collect(groupingBy(StudentPartitioningGrouping::getGrade,
                        groupingBy(StudentPartitioningGrouping::getBan,
                                collectingAndThen(
                                        maxBy(Comparator.comparingInt(StudentPartitioningGrouping::getScore)),
                                        Optional::get
                                ))));

        for (Map<Integer, StudentPartitioningGrouping> stuClass :topStuByGradeAndClass.values()){
            for (StudentPartitioningGrouping s : stuClass.values()) {
                System.out.println(s);
            }
        }

        System.out.printf("%n6. 다중그룹화 + 통계 (학년별, 반별 성적그룹)%n");

        Map<String, Set<StudentPartitioningGrouping.Level>> stuByScoreGroup = Stream.of(stuArr)
                .collect(groupingBy(s -> s.getGrade() + " - " + s.getBan(),
                        mapping(s -> {
                            if (s.getScore() >= 200) {
                                return StudentPartitioningGrouping.Level.HIGH;
                            } else if (s.getScore() >= 100) {
                                return StudentPartitioningGrouping.Level.MID;
                            } else {
                                return StudentPartitioningGrouping.Level.LOW;
                            }
                        }, toSet())
                ));

        Set<String> keySet = stuByScoreGroup.keySet();

        for (String key : keySet) {
            System.out.println("[" + key + "]" + stuByScoreGroup.get(key));
        }

    }




    private static StudentPartitioningGrouping[] createStudents() {
        StudentPartitioningGrouping[] stuArr = {
                new StudentPartitioningGrouping("가나다", true, 1, 1, 300),
                new StudentPartitioningGrouping("라마바", false, 1, 2, 200),
                new StudentPartitioningGrouping("사아자", true, 1, 3, 100),
                new StudentPartitioningGrouping("차카타", false, 1, 2, 150),
                new StudentPartitioningGrouping("파하하", true, 1, 1, 50),
                new StudentPartitioningGrouping("ABC", false, 1, 2, 210),
                new StudentPartitioningGrouping("DEF", true, 1, 3, 150),
                new StudentPartitioningGrouping("GHI", false, 1, 2, 250),
                new StudentPartitioningGrouping("JKL", true, 2, 1, 300),
                new StudentPartitioningGrouping("NMO", false, 2, 2, 200),
                new StudentPartitioningGrouping("PQR", true, 2, 3, 130),
                new StudentPartitioningGrouping("STU", false, 2, 2, 170),
                new StudentPartitioningGrouping("VWX", true, 2, 1, 230),
                new StudentPartitioningGrouping("YZ", false, 2, 3, 10),
        };
        return stuArr;
    }

}


