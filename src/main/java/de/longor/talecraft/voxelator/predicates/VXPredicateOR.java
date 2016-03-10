package de.longor.talecraft.voxelator.predicates;

import net.minecraft.util.BlockPos;
import de.longor.talecraft.util.MutableBlockPos;
import de.longor.talecraft.voxelator.CachedWorldDiff;
import de.longor.talecraft.voxelator.VXPredicate;

public final class VXPredicateOR extends VXPredicate {
	private final VXPredicate[] predicates;
	
	public VXPredicateOR(VXPredicate... predicates) {
		this.predicates = predicates;
	}
	
	@Override
	public boolean test(BlockPos pos, BlockPos center, MutableBlockPos offset, CachedWorldDiff fworld) {
		for(VXPredicate predicate : predicates) {
			if(predicate.test(pos, center, offset, fworld))
				return true;
		}
		return false;
	}
}
