package cinema;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Schedule {
    private Set<Seance>seances = new TreeSet<>();

    public Set<Seance> getSeances() {
        return seances;
    }

    public void addSeance (Seance seance){
        seances.add(seance);
    }

    public void removeSeance (Seance seance){
        seances = seances.stream().filter(a-> !a.equals(seance)).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(seances, schedule.seances);
    }

    @Override
    public int hashCode() {

        return Objects.hash(seances);
    }
}
