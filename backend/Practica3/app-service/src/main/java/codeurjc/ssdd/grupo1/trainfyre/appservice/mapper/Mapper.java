package codeurjc.ssdd.grupo1.trainfyre.appservice.mapper;

@FunctionalInterface
public interface Mapper<I, O> {

    O map(I input);
}
