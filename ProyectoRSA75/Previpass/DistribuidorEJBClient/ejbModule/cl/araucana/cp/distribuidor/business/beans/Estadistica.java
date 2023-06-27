package cl.araucana.cp.distribuidor.business.beans;

import java.io.Serializable;

public class Estadistica implements Serializable
{
	private static final long serialVersionUID = -8027660892917189377L;
	private long cacheHits;
	private long cacheMisses;
	private long inMemoryHits;
	private long onDiskHits;
	private long objectCount;
	
	private String region;
	
	public Estadistica()
	{
		super();
	}

	public Estadistica(String region)
	{
		super();
		this.region = region;
	}

	public Estadistica(long cacheHits, long cacheMisses, long inMemoryHits, long objectCount, long onDiskHits, String region)
	{
		super();
		this.cacheHits = cacheHits;
		this.cacheMisses = cacheMisses;
		this.inMemoryHits = inMemoryHits;
		this.objectCount = objectCount;
		this.onDiskHits = onDiskHits;
		this.region = region;
	}
	
	public void add(Estadistica e)
	{
		this.cacheHits += e.getCacheHits();
		this.cacheMisses += e.getCacheMisses();
		this.inMemoryHits += e.getInMemoryHits();
		this.objectCount += e.getObjectCount();
		this.onDiskHits += e.getOnDiskHits();
	}

	public long getCacheHits()
	{
		return this.cacheHits;
	}
	public void setCacheHits(long cacheHits)
	{
		this.cacheHits = cacheHits;
	}
	public long getCacheMisses()
	{
		return this.cacheMisses;
	}
	public void setCacheMisses(long cacheMisses)
	{
		this.cacheMisses = cacheMisses;
	}
	public long getInMemoryHits()
	{
		return this.inMemoryHits;
	}
	public void setInMemoryHits(long inMemoryHits)
	{
		this.inMemoryHits = inMemoryHits;
	}
	public long getObjectCount()
	{
		return this.objectCount;
	}
	public void setObjectCount(long objectCount)
	{
		this.objectCount = objectCount;
	}
	public long getOnDiskHits()
	{
		return this.onDiskHits;
	}
	public void setOnDiskHits(long onDiskHits)
	{
		this.onDiskHits = onDiskHits;
	}
	public String getRegion()
	{
		return this.region.trim();
	}
	public void setRegion(String region)
	{
		this.region = region;
	}
}
