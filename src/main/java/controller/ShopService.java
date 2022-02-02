package controller;

import model.repository.BaseRepository;

import java.util.List;

public abstract class ShopService<E, R extends BaseRepository<E>> implements BaseRepository<E> {
    R r;

    public ShopService(R r) {
        this.r = r;
    }

    @Override
    public void save(E e) {
        r.save(e);
    }

    @Override
    public void delete(Integer id) {
        r.delete(id);
    }

    @Override
    public void update(E e) {
        r.update(e);
    }

    @Override
    public List<E> findAll() {
        return r.findAll();
    }

    @Override
    public E findById(Integer id) {
        return r.findById(id);
    }
}
