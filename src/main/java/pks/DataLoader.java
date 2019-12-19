package pks;

public interface DataLoader<T> {

    T load() throws Exception;
}
