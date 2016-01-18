package se.github.datalab.repository.jpastorage;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import se.github.datalab.model.Product;
import se.github.datalab.repository.ProductRepository;
import se.github.datalab.statuses.ProductStatus;

public class JpaProductRepository extends JpaAbstractRepository<Product> implements ProductRepository
{

	public JpaProductRepository(EntityManagerFactory factory)
	{
		super(factory, Product.class);
	}

	@Override
	public Collection<Product> getAll()
	{
		return query("Product.GetAll", Product.class);
	}

	@Override
	public List<Product> getByName(String name)
	{
		EntityManager manager = factory.createEntityManager();
		try
		{
			TypedQuery<Product> query = manager.createNamedQuery("Product.GetProduct", Product.class);
			query.setParameter("name", "%" + name + "%");
			return query.getResultList();
		}
		catch (Exception e)
		{
			throw new RuntimeException();
		}
		finally
		{
			manager.close();
		}
	}

	@Override
	public List<Product> getByStatus(ProductStatus status)
	{
		EntityManager manager = factory.createEntityManager();
		try
		{
			TypedQuery<Product> result = manager.createNamedQuery("Product.GetByStatus", Product.class);
			result.setParameter("status", status.ordinal());
			return result.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			manager.close();
		}
		return null;
	}

	@Override
	public List<Product> getByCost(double price)
	{
		EntityManager manager = factory.createEntityManager();
		try
		{
			TypedQuery<Product> result = manager.createNamedQuery("Product.GetByCost", Product.class);
			result.setParameter("price", price);
			return result.getResultList();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			manager.close();
		}
		return null;

	}

}
