package be.vdab.toysforboys.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.toysforboys.entities.Order;
import be.vdab.toysforboys.valueobjects.Orderdetail;

public interface OrderRepository {
	Optional<Order> read(long id);
	List<Order> findAll();
}
